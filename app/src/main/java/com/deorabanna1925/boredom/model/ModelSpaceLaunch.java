package com.deorabanna1925.boredom.model;

public class ModelSpaceLaunch {

    private String id;
    private String url;
    private String slug;
    private String name;
    private Status status;
    private String last_updated;
    private String net;
    private String window_end;
    private String window_start;
    private Integer probability;
    private String holdreason;
    private String failreason;
    private String hashtag;
    private LaunchServiceProvider launch_service_provider;
    private Rocket rocket;
    private Mission mission;
    private Pad pad;
    private Boolean webcast_live;
    private String image;
    private String infographic;

    public ModelSpaceLaunch() {
    }

    public ModelSpaceLaunch(String id, String url, String slug, String name, Status status, String last_updated, String net, String window_end, String window_start, Integer probability, String holdreason, String failreason, String hashtag, LaunchServiceProvider launch_service_provider, Rocket rocket, Mission mission, Pad pad, Boolean webcast_live, String image, String infographic) {
        this.id = id;
        this.url = url;
        this.slug = slug;
        this.name = name;
        this.status = status;
        this.last_updated = last_updated;
        this.net = net;
        this.window_end = window_end;
        this.window_start = window_start;
        this.probability = probability;
        this.holdreason = holdreason;
        this.failreason = failreason;
        this.hashtag = hashtag;
        this.launch_service_provider = launch_service_provider;
        this.rocket = rocket;
        this.mission = mission;
        this.pad = pad;
        this.webcast_live = webcast_live;
        this.image = image;
        this.infographic = infographic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(String last_updated) {
        this.last_updated = last_updated;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public String getWindow_end() {
        return window_end;
    }

    public void setWindow_end(String window_end) {
        this.window_end = window_end;
    }

    public String getWindow_start() {
        return window_start;
    }

    public void setWindow_start(String window_start) {
        this.window_start = window_start;
    }

    public Integer getProbability() {
        return probability;
    }

    public void setProbability(Integer probability) {
        this.probability = probability;
    }

    public String getHoldreason() {
        return holdreason;
    }

    public void setHoldreason(String holdreason) {
        this.holdreason = holdreason;
    }

    public String getFailreason() {
        return failreason;
    }

    public void setFailreason(String failreason) {
        this.failreason = failreason;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public LaunchServiceProvider getLaunch_service_provider() {
        return launch_service_provider;
    }

    public void setLaunch_service_provider(LaunchServiceProvider launch_service_provider) {
        this.launch_service_provider = launch_service_provider;
    }

    public Rocket getRocket() {
        return rocket;
    }

    public void setRocket(Rocket rocket) {
        this.rocket = rocket;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public Pad getPad() {
        return pad;
    }

    public void setPad(Pad pad) {
        this.pad = pad;
    }

    public Boolean getWebcast_live() {
        return webcast_live;
    }

    public void setWebcast_live(Boolean webcast_live) {
        this.webcast_live = webcast_live;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getInfographic() {
        return infographic;
    }

    public void setInfographic(String infographic) {
        this.infographic = infographic;
    }

    public class Status {

        private Integer id;
        private String name;
        private String abbrev;
        private String description;

        public Status() {
        }

        public Status(Integer id, String name, String abbrev, String description) {
            this.id = id;
            this.name = name;
            this.abbrev = abbrev;
            this.description = description;
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

        public String getAbbrev() {
            return abbrev;
        }

        public void setAbbrev(String abbrev) {
            this.abbrev = abbrev;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public class LaunchServiceProvider {

        private Integer id;
        private String url;
        private String name;
        private String type;

        public LaunchServiceProvider() {
        }

        public LaunchServiceProvider(Integer id, String url, String name, String type) {
            this.id = id;
            this.url = url;
            this.name = name;
            this.type = type;
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
    }

    public class Rocket {

        private Integer id;
        private Configuration configuration;

        public Rocket() {
        }

        public Rocket(Integer id, Configuration configuration) {
            this.id = id;
            this.configuration = configuration;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Configuration getConfiguration() {
            return configuration;
        }

        public void setConfiguration(Configuration configuration) {
            this.configuration = configuration;
        }

        public class Configuration {

            private Integer id;
            private String url;
            private String name;
            private String family;
            private String full_name;
            private String variant;

            public Configuration() {
            }

            public Configuration(Integer id, String url, String name, String family, String full_name, String variant) {
                this.id = id;
                this.url = url;
                this.name = name;
                this.family = family;
                this.full_name = full_name;
                this.variant = variant;
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

            public String getFamily() {
                return family;
            }

            public void setFamily(String family) {
                this.family = family;
            }

            public String getFull_name() {
                return full_name;
            }

            public void setFull_name(String full_name) {
                this.full_name = full_name;
            }

            public String getVariant() {
                return variant;
            }

            public void setVariant(String variant) {
                this.variant = variant;
            }

        }

    }

    public class Mission {

        private Integer id;
        private String name;
        private String description;
        private String launch_designator;
        private String type;
        private Orbit orbit;

        public Mission() {
        }

        public Mission(Integer id, String name, String description, String launch_designator, String type, Orbit orbit) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.launch_designator = launch_designator;
            this.type = type;
            this.orbit = orbit;
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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLaunch_designator() {
            return launch_designator;
        }

        public void setLaunch_designator(String launch_designator) {
            this.launch_designator = launch_designator;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Orbit getOrbit() {
            return orbit;
        }

        public void setOrbit(Orbit orbit) {
            this.orbit = orbit;
        }

        public class Orbit {

            private Integer id;
            private String name;
            private String abbrev;

            public Orbit() {
            }

            public Orbit(Integer id, String name, String abbrev) {
                this.id = id;
                this.name = name;
                this.abbrev = abbrev;
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

            public String getAbbrev() {
                return abbrev;
            }

            public void setAbbrev(String abbrev) {
                this.abbrev = abbrev;
            }

        }

    }

    public class Pad {

        private Integer id;
        private String url;
        private String agency_id;
        private String name;
        private String info_url;
        private String wiki_url;
        private String map_url;
        private String latitude;
        private String longitude;
        private Location location;
        private String map_image;
        private Integer total_launch_count;

        public Pad() {
        }

        public Pad(Integer id, String url, String agency_id, String name, String info_url, String wiki_url, String map_url, String latitude, String longitude, Location location, String map_image, Integer total_launch_count) {
            this.id = id;
            this.url = url;
            this.agency_id = agency_id;
            this.name = name;
            this.info_url = info_url;
            this.wiki_url = wiki_url;
            this.map_url = map_url;
            this.latitude = latitude;
            this.longitude = longitude;
            this.location = location;
            this.map_image = map_image;
            this.total_launch_count = total_launch_count;
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

        public String getAgency_id() {
            return agency_id;
        }

        public void setAgency_id(String agency_id) {
            this.agency_id = agency_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getInfo_url() {
            return info_url;
        }

        public void setInfo_url(String info_url) {
            this.info_url = info_url;
        }

        public String getWiki_url() {
            return wiki_url;
        }

        public void setWiki_url(String wiki_url) {
            this.wiki_url = wiki_url;
        }

        public String getMap_url() {
            return map_url;
        }

        public void setMap_url(String map_url) {
            this.map_url = map_url;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public String getMap_image() {
            return map_image;
        }

        public void setMap_image(String map_image) {
            this.map_image = map_image;
        }

        public Integer getTotal_launch_count() {
            return total_launch_count;
        }

        public void setTotal_launch_count(Integer total_launch_count) {
            this.total_launch_count = total_launch_count;
        }

        public class Location {

            private Integer id;
            private String url;
            private String name;
            private String country_code;
            private String map_image;
            private Integer total_launch_count;
            private Integer total_landing_count;

            public Location() {
            }

            public Location(Integer id, String url, String name, String country_code, String map_image, Integer total_launch_count, Integer total_landing_count) {
                this.id = id;
                this.url = url;
                this.name = name;
                this.country_code = country_code;
                this.map_image = map_image;
                this.total_launch_count = total_launch_count;
                this.total_landing_count = total_landing_count;
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

            public String getCountry_code() {
                return country_code;
            }

            public void setCountry_code(String country_code) {
                this.country_code = country_code;
            }

            public String getMap_image() {
                return map_image;
            }

            public void setMap_image(String map_image) {
                this.map_image = map_image;
            }

            public Integer getTotal_launch_count() {
                return total_launch_count;
            }

            public void setTotal_launch_count(Integer total_launch_count) {
                this.total_launch_count = total_launch_count;
            }

            public Integer getTotal_landing_count() {
                return total_landing_count;
            }

            public void setTotal_landing_count(Integer total_landing_count) {
                this.total_landing_count = total_landing_count;
            }
        }

    }

}
