package com.deorabanna1925.boredom.model;

import java.util.ArrayList;

public class ModelTvShow {

    private Double score;
    private Show show;

    public ModelTvShow() {
    }

    public ModelTvShow(Double score, Show show) {
        this.score = score;
        this.show = show;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public class Show {
        private Integer id;
        private String url;
        private String name;
        private String type;
        private String language;
        private ArrayList<String> genres;
        private String status;
        private Integer runtime;
        private Integer averageRuntime;
        private String premiered;
        private String officialSite;
        private Schedule schedule;
        private Rating rating;
        private Integer weight;
        private Network network;
        private WebChannel webChannel;
        private Network.Country dvdCountry;
        private Externals externals;
        private Image image;
        private String summary;
        private Integer updated;
        private Links _links;

        public Show() {
        }

        public Show(Integer id, String url, String name, String type, String language, ArrayList<String> genres, String status, Integer runtime, Integer averageRuntime, String premiered, String officialSite, Schedule schedule, Rating rating, Integer weight, Network network, WebChannel webChannel, Network.Country dvdCountry, Externals externals, Image image, String summary, Integer updated, Links _links) {
            this.id = id;
            this.url = url;
            this.name = name;
            this.type = type;
            this.language = language;
            this.genres = genres;
            this.status = status;
            this.runtime = runtime;
            this.averageRuntime = averageRuntime;
            this.premiered = premiered;
            this.officialSite = officialSite;
            this.schedule = schedule;
            this.rating = rating;
            this.weight = weight;
            this.network = network;
            this.webChannel = webChannel;
            this.dvdCountry = dvdCountry;
            this.externals = externals;
            this.image = image;
            this.summary = summary;
            this.updated = updated;
            this._links = _links;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public ArrayList<String> getGenres() {
            return genres;
        }

        public void setGenres(ArrayList<String> genres) {
            this.genres = genres;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Integer getRuntime() {
            return runtime;
        }

        public void setRuntime(Integer runtime) {
            this.runtime = runtime;
        }

        public Integer getAverageRuntime() {
            return averageRuntime;
        }

        public void setAverageRuntime(Integer averageRuntime) {
            this.averageRuntime = averageRuntime;
        }

        public String getPremiered() {
            return premiered;
        }

        public void setPremiered(String premiered) {
            this.premiered = premiered;
        }

        public String getOfficialSite() {
            return officialSite;
        }

        public void setOfficialSite(String officialSite) {
            this.officialSite = officialSite;
        }

        public Schedule getSchedule() {
            return schedule;
        }

        public void setSchedule(Schedule schedule) {
            this.schedule = schedule;
        }

        public Rating getRating() {
            return rating;
        }

        public void setRating(Rating rating) {
            this.rating = rating;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public Network getNetwork() {
            return network;
        }

        public void setNetwork(Network network) {
            this.network = network;
        }

        public WebChannel getWebChannel() {
            return webChannel;
        }

        public void setWebChannel(WebChannel webChannel) {
            this.webChannel = webChannel;
        }

        public Network.Country getDvdCountry() {
            return dvdCountry;
        }

        public void setDvdCountry(Network.Country dvdCountry) {
            this.dvdCountry = dvdCountry;
        }

        public Externals getExternals() {
            return externals;
        }

        public void setExternals(Externals externals) {
            this.externals = externals;
        }

        public Image getImage() {
            return image;
        }

        public void setImage(Image image) {
            this.image = image;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public Integer getUpdated() {
            return updated;
        }

        public void setUpdated(Integer updated) {
            this.updated = updated;
        }

        public Links get_links() {
            return _links;
        }

        public void set_links(Links _links) {
            this._links = _links;
        }

        public class Schedule {

            private String time;
            private ArrayList<String> days;

            public Schedule() {
            }

            public Schedule(String time, ArrayList<String> days) {
                this.time = time;
                this.days = days;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public ArrayList<String> getDays() {
                return days;
            }

            public void setDays(ArrayList<String> days) {
                this.days = days;
            }
        }

        public class Rating {

            private Double average;

            public Rating() {
            }

            public Rating(Double average) {
                this.average = average;
            }

            public Double getAverage() {
                return average;
            }

            public void setAverage(Double average) {
                this.average = average;
            }
        }

        public class Network {

            private Integer id;
            private String name;
            private Country country;

            public Network() {
            }

            public Network(Integer id, String name, Country country) {
                this.id = id;
                this.name = name;
                this.country = country;
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Country getCountry() {
                return country;
            }

            public void setCountry(Country country) {
                this.country = country;
            }

            public class Country {

                private String name;
                private String code;
                private String timezone;

                public Country() {
                }

                public Country(String name, String code, String timezone) {
                    this.name = name;
                    this.code = code;
                    this.timezone = timezone;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }

                public String getTimezone() {
                    return timezone;
                }

                public void setTimezone(String timezone) {
                    this.timezone = timezone;
                }
            }

        }

        public class WebChannel {

            private Integer id;
            private String name;
            private Network.Country country;

            public WebChannel() {
            }

            public WebChannel(Integer id, String name, Network.Country country) {
                this.id = id;
                this.name = name;
                this.country = country;
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Network.Country getCountry() {
                return country;
            }

            public void setCountry(Network.Country country) {
                this.country = country;
            }
        }

        public class Externals {

            private Integer tvrage;
            private Integer thetvdb;
            private String imdb;

            public Externals() {
            }

            public Externals(Integer tvrage, Integer thetvdb, String imdb) {
                this.tvrage = tvrage;
                this.thetvdb = thetvdb;
                this.imdb = imdb;
            }

            public Integer getTvrage() {
                return tvrage;
            }

            public void setTvrage(Integer tvrage) {
                this.tvrage = tvrage;
            }

            public Integer getThetvdb() {
                return thetvdb;
            }

            public void setThetvdb(Integer thetvdb) {
                this.thetvdb = thetvdb;
            }

            public String getImdb() {
                return imdb;
            }

            public void setImdb(String imdb) {
                this.imdb = imdb;
            }
        }

        public class Image {

            private String medium;
            private String original;

            public Image() {
            }

            public Image(String medium, String original) {
                this.medium = medium;
                this.original = original;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }

            public String getOriginal() {
                return original;
            }

            public void setOriginal(String original) {
                this.original = original;
            }
        }

        public class Links {

            private Self self;
            private PreviousEpisode previousepisode;

            public Links() {
            }

            public Links(Self self, PreviousEpisode previousepisode) {
                this.self = self;
                this.previousepisode = previousepisode;
            }

            public Self getSelf() {
                return self;
            }

            public void setSelf(Self self) {
                this.self = self;
            }

            public PreviousEpisode getPreviousepisode() {
                return previousepisode;
            }

            public void setPreviousepisode(PreviousEpisode previousepisode) {
                this.previousepisode = previousepisode;
            }

            public class Self {

                private String href;

                public Self() {
                }

                public Self(String href) {
                    this.href = href;
                }

                public String getHref() {
                    return href;
                }

                public void setHref(String href) {
                    this.href = href;
                }
            }

            public class PreviousEpisode {

                private String href;

                public PreviousEpisode() {
                }

                public PreviousEpisode(String href) {
                    this.href = href;
                }

                public String getHref() {
                    return href;
                }

                public void setHref(String href) {
                    this.href = href;
                }
            }
        }
    }

}
