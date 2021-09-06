package com.deorabanna1925.boredom.adapter;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.deorabanna1925.boredom.databinding.ItemCountryBinding;
import com.deorabanna1925.boredom.model.ModelCollege;
import com.deorabanna1925.boredom.model.ModelCountry;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<ModelCountry> arrayList;

    public CountryAdapter(Context context, ArrayList<ModelCountry> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CountryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCountryBinding binding = ItemCountryBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.ViewHolder holder, int position) {
        ModelCountry model = arrayList.get(position);

        holder.nativeName.setText(model.getNativeName());
        holder.name.setText(model.getName());
        holder.capital.setText(model.getCapital());
        holder.alpha2Code.setText(model.getAlpha2Code());
        holder.alpha3Code.setText(model.getAlpha3Code());
        holder.topLevelDomain.setText(model.getTopLevelDomain().get(0));

        String flag = "https://flagcdn.com/w640/" + model.getAlpha2Code().toLowerCase() + ".webp";

        Glide.with(context).load(flag).into(holder.flag);

        String area = model.getArea() + " km" + "\u00B2";
        holder.area.setText(area);
        holder.population.setText(model.getPopulation());

        String time = "";
        for (String timezone : model.getTimezones()) {
            time += " • ";
            time += getTimezoneFromUTC(timezone);  // FORMAT >> UTC+05:30 && UTC-04:00 && UTC
            holder.timezones.setText(time);
            time += "\n";
        }

        String utc = "";
        for (String utcStamp : model.getTimezones()) {
            utc += utcStamp;
            holder.utc.setText(utc);
            utc += "\n";
        }

        String language = "";
        for(ModelCountry.Languages languages : model.getLanguages()) {
            language += languages.getName();
            holder.language.setText(language);
            language += "\n";
        }

        String nativeLanguage = "";
        for(ModelCountry.Languages languages : model.getLanguages()) {
            nativeLanguage += languages.getNativeName();
            holder.nativeLanguage.setText(nativeLanguage);
            nativeLanguage += "\n";
        }

        String symbol = "";
        for(ModelCountry.Currencies currency : model.getCurrencies()) {
            symbol += currency.getSymbol();
            holder.currencySymbol.setText(symbol);
            symbol += "\n";
        }
        String name = "";
        for(ModelCountry.Currencies currency : model.getCurrencies()) {
            name += currency.getName();
            holder.currencyName.setText(name);
            name += "\n";
        }
        String code = "";
        for(ModelCountry.Currencies currency : model.getCurrencies()) {
            code += currency.getCode();
            holder.currencyCode.setText(code);
            code += "\n";
        }

        holder.subRegion.setText(model.getSubregion());
        holder.region.setText(model.getRegion());

        holder.searchGoogle.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, model.getName()); // query contains search string
            context.startActivity(intent);
        });

        holder.viewOnMap.setOnClickListener(view -> {
            String uri = String.format(Locale.ENGLISH,
                    "http://maps.google.com/maps?q=loc:%f,%f",
                    model.getLatlng().get(0),
                    model.getLatlng().get(1)
            );
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            context.startActivity(intent);
        });

        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.recyclerView.setNestedScrollingEnabled(false);
        holder.recyclerView.setHasFixedSize(true);

        getColleges(holder,model);

    }

    private void getColleges(ViewHolder holder, ModelCountry model) {
        String url = "http://universities.hipolabs.com/search?country=" + model.getName();
        final RequestQueue queue = Volley.newRequestQueue(context);
        queue.getCache().clear();
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONArray jsonArray = new JSONArray(response);
                Gson gson = new Gson();
                String jsonOutput = jsonArray.toString();
                Type listType = new TypeToken<ArrayList<ModelCollege>>() {
                }.getType();
                ArrayList<ModelCollege> arrayList = gson.fromJson(jsonOutput, listType);
                if(arrayList.size()!=0){
                    String collegesInCountry = "Colleges in " + model.getName();
                    holder.collegesInCountry.setText(collegesInCountry);
                }
                holder.recyclerView.setAdapter(new CollegeAdapter(context, arrayList));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
//            progressBar.setVisibility(View.GONE);
        });
        queue.add(request);
    }

    private String getTimezoneFromUTC(String timezone) {
        String extract; // convert UTC+05:30 --> +05:30  && UTC --> +00:00
        if (timezone.equals("UTC")) extract = "+00:00";
        else extract = timezone.substring(3);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            ZoneOffset zoneOffSet = ZoneOffset.of(extract);
            OffsetDateTime offsetDateTime = OffsetDateTime.now(zoneOffSet);
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM dd, hh:mm a");
            return fmt.format(offsetDateTime);
        }
        return extract;
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nativeName;
        public TextView name;
        public TextView capital;
        public ImageView flag;
        public TextView alpha2Code;
        public TextView alpha3Code;
        public TextView topLevelDomain;
        public TextView timezones;
        public TextView utc;
        public TextView area;
        public TextView subRegion;
        public TextView region;
        public TextView population;
        public TextView searchGoogle;
        public TextView viewOnMap;
        public TextView language;
        public TextView nativeLanguage;
        public TextView currencyCode;
        public TextView currencyName;
        public TextView currencySymbol;
        public TextView collegesInCountry;
        public RecyclerView recyclerView;

        public ViewHolder(@NonNull ItemCountryBinding binding) {
            super(binding.getRoot());

            nativeName = binding.nativeName;
            name = binding.name;
            capital = binding.capital;
            flag = binding.flag;
            alpha2Code = binding.alpha2Code;
            alpha3Code = binding.alpha3Code;
            topLevelDomain = binding.topLevelDomain;
            timezones = binding.timezones;
            utc = binding.utc;
            area = binding.area;
            population = binding.population;
            subRegion = binding.subRegion;
            region = binding.region;
            searchGoogle = binding.searchGoogle;
            viewOnMap = binding.viewOnMap;
            language = binding.language;
            nativeLanguage = binding.nativeLanguage;
            currencyCode = binding.currencyCode;
            currencyName = binding.currencyName;
            currencySymbol = binding.currencySymbol;
            collegesInCountry = binding.collegesInCountry;
            recyclerView = binding.recyclerView;
        }

    }
}
