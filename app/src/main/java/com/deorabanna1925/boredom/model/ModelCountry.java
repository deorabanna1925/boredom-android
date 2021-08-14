package com.deorabanna1925.boredom.model;

import java.util.ArrayList;

public class ModelCountry {

    private String name;
    private ArrayList<String> topLevelDomain;
    private String alpha2Code;
    private String alpha3Code;
    private ArrayList<String> callingCodes;
    private String capital;
    private ArrayList<String> altSpellings;
    private String region;
    private String subregion;
    private String population;
    private ArrayList<Double> latlng;
    private String demonym;
    private String area;
    private String gini;
    private ArrayList<String> timezones;
    private ArrayList<String> borders;
    private String nativeName;
    private String numericCode;
    private ArrayList<Currencies> currencies;
    private ArrayList<Languages> languages;
    private Translations translations;
    private String flag;
    private ArrayList<RegionalBlocs> regionalBlocs;
    private String cioc;

    public ModelCountry(String name, ArrayList<String> topLevelDomain, String alpha2Code, String alpha3Code, ArrayList<String> callingCodes, String capital, ArrayList<String> altSpellings, String region, String subregion, String population, ArrayList<Double> latlng, String demonym, String area, String gini, ArrayList<String> timezones, ArrayList<String> borders, String nativeName, String numericCode, ArrayList<Currencies> currencies, ArrayList<Languages> languages, Translations translations, String flag, ArrayList<RegionalBlocs> regionalBlocs, String cioc) {
        this.name = name;
        this.topLevelDomain = topLevelDomain;
        this.alpha2Code = alpha2Code;
        this.alpha3Code = alpha3Code;
        this.callingCodes = callingCodes;
        this.capital = capital;
        this.altSpellings = altSpellings;
        this.region = region;
        this.subregion = subregion;
        this.population = population;
        this.latlng = latlng;
        this.demonym = demonym;
        this.area = area;
        this.gini = gini;
        this.timezones = timezones;
        this.borders = borders;
        this.nativeName = nativeName;
        this.numericCode = numericCode;
        this.currencies = currencies;
        this.languages = languages;
        this.translations = translations;
        this.flag = flag;
        this.regionalBlocs = regionalBlocs;
        this.cioc = cioc;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getTopLevelDomain() {
        return topLevelDomain;
    }

    public void setTopLevelDomain(ArrayList<String> topLevelDomain) {
        this.topLevelDomain = topLevelDomain;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public ArrayList<String> getCallingCodes() {
        return callingCodes;
    }

    public void setCallingCodes(ArrayList<String> callingCodes) {
        this.callingCodes = callingCodes;
    }

    public ArrayList<String> getAltSpellings() {
        return altSpellings;
    }

    public void setAltSpellings(ArrayList<String> altSpellings) {
        this.altSpellings = altSpellings;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public ArrayList<Double> getLatlng() {
        return latlng;
    }

    public void setLatlng(ArrayList<Double> latlng) {
        this.latlng = latlng;
    }

    public String getDemonym() {
        return demonym;
    }

    public void setDemonym(String demonym) {
        this.demonym = demonym;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getGini() {
        return gini;
    }

    public void setGini(String gini) {
        this.gini = gini;
    }

    public ArrayList<String> getTimezones() {
        return timezones;
    }

    public void setTimezones(ArrayList<String> timezones) {
        this.timezones = timezones;
    }

    public ArrayList<String> getBorders() {
        return borders;
    }

    public void setBorders(ArrayList<String> borders) {
        this.borders = borders;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public String getNumericCode() {
        return numericCode;
    }

    public void setNumericCode(String numericCode) {
        this.numericCode = numericCode;
    }

    public ArrayList<Currencies> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(ArrayList<Currencies> currencies) {
        this.currencies = currencies;
    }

    public ArrayList<Languages> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<Languages> languages) {
        this.languages = languages;
    }

    public Translations getTranslations() {
        return translations;
    }

    public void setTranslations(Translations translations) {
        this.translations = translations;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public ArrayList<RegionalBlocs> getRegionalBlocs() {
        return regionalBlocs;
    }

    public void setRegionalBlocs(ArrayList<RegionalBlocs> regionalBlocs) {
        this.regionalBlocs = regionalBlocs;
    }

    public String getCioc() {
        return cioc;
    }

    public void setCioc(String cioc) {
        this.cioc = cioc;
    }

    public class Translations {

        private String de;
        private String es;
        private String fr;
        private String ja;
        private String it;
        private String br;
        private String pt;
        private String nl;
        private String hr;
        private String fa;

        public Translations(String de, String es, String fr, String ja, String it, String br, String pt, String nl, String hr, String fa) {
            this.de = de;
            this.es = es;
            this.fr = fr;
            this.ja = ja;
            this.it = it;
            this.br = br;
            this.pt = pt;
            this.nl = nl;
            this.hr = hr;
            this.fa = fa;
        }

        public String getDe() {
            return de;
        }

        public void setDe(String de) {
            this.de = de;
        }

        public String getEs() {
            return es;
        }

        public void setEs(String es) {
            this.es = es;
        }

        public String getFr() {
            return fr;
        }

        public void setFr(String fr) {
            this.fr = fr;
        }

        public String getJa() {
            return ja;
        }

        public void setJa(String ja) {
            this.ja = ja;
        }

        public String getIt() {
            return it;
        }

        public void setIt(String it) {
            this.it = it;
        }

        public String getBr() {
            return br;
        }

        public void setBr(String br) {
            this.br = br;
        }

        public String getPt() {
            return pt;
        }

        public void setPt(String pt) {
            this.pt = pt;
        }

        public String getNl() {
            return nl;
        }

        public void setNl(String nl) {
            this.nl = nl;
        }

        public String getHr() {
            return hr;
        }

        public void setHr(String hr) {
            this.hr = hr;
        }

        public String getFa() {
            return fa;
        }

        public void setFa(String fa) {
            this.fa = fa;
        }
    }

    public class RegionalBlocs {

        private String acronym;
        private String name;
        private ArrayList<String> otherAcronyms;
        private ArrayList<String> otherNames;

        public RegionalBlocs(String acronym, String name, ArrayList<String> otherAcronyms, ArrayList<String> otherNames) {
            this.acronym = acronym;
            this.name = name;
            this.otherAcronyms = otherAcronyms;
            this.otherNames = otherNames;
        }

        public String getAcronym() {
            return acronym;
        }

        public void setAcronym(String acronym) {
            this.acronym = acronym;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ArrayList<String> getOtherAcronyms() {
            return otherAcronyms;
        }

        public void setOtherAcronyms(ArrayList<String> otherAcronyms) {
            this.otherAcronyms = otherAcronyms;
        }

        public ArrayList<String> getOtherNames() {
            return otherNames;
        }

        public void setOtherNames(ArrayList<String> otherNames) {
            this.otherNames = otherNames;
        }
    }

    public class Currencies {

        private String code;
        private String name;
        private String symbol;

        public Currencies(String code, String name, String symbol) {
            this.code = code;
            this.name = name;
            this.symbol = symbol;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }
    }

    public class Languages {

        private String iso639_1;
        private String iso639_2;
        private String name;
        private String nativeName;

        public Languages(String iso639_1, String iso639_2, String name, String nativeName) {
            this.iso639_1 = iso639_1;
            this.iso639_2 = iso639_2;
            this.name = name;
            this.nativeName = nativeName;
        }

        public String getIso639_1() {
            return iso639_1;
        }

        public void setIso639_1(String iso639_1) {
            this.iso639_1 = iso639_1;
        }

        public String getIso639_2() {
            return iso639_2;
        }

        public void setIso639_2(String iso639_2) {
            this.iso639_2 = iso639_2;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNativeName() {
            return nativeName;
        }

        public void setNativeName(String nativeName) {
            this.nativeName = nativeName;
        }
    }
}

