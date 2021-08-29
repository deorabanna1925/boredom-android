package com.deorabanna1925.boredom.model;

public class ModelRandomUser {

    private String gender;
    private Name name;
    private Location location;
    private String email;
    private Login login;
    private Dob dob;
    private Registered registered;
    private String phone;
    private String cell;
    private Id id;
    private Picture picture;
    private String nat;

    public ModelRandomUser() {
    }

    public ModelRandomUser(String gender, Name name, Location location, String email, Login login, Dob dob, Registered registered, String phone, String cell, Id id, Picture picture, String nat) {
        this.gender = gender;
        this.name = name;
        this.location = location;
        this.email = email;
        this.login = login;
        this.dob = dob;
        this.registered = registered;
        this.phone = phone;
        this.cell = cell;
        this.id = id;
        this.picture = picture;
        this.nat = nat;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Dob getDob() {
        return dob;
    }

    public void setDob(Dob dob) {
        this.dob = dob;
    }

    public Registered getRegistered() {
        return registered;
    }

    public void setRegistered(Registered registered) {
        this.registered = registered;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public String getNat() {
        return nat;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }

    public class Name {

        private String title;
        private String first;
        private String last;

        public Name() {
        }

        public Name(String title, String first, String last) {
            this.title = title;
            this.first = first;
            this.last = last;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public String getLast() {
            return last;
        }

        public void setLast(String last) {
            this.last = last;
        }
    }

    public class Location {

        private Street street;
        private String city;
        private String state;
        private String country;
        private Integer postcode;
        private Coordinates coordinates;
        private Timezone timezone;

        public Location() {
        }

        public Location(Street street, String city, String state, String country, Integer postcode, Coordinates coordinates, Timezone timezone) {
            this.street = street;
            this.city = city;
            this.state = state;
            this.country = country;
            this.postcode = postcode;
            this.coordinates = coordinates;
            this.timezone = timezone;
        }

        public Street getStreet() {
            return street;
        }

        public void setStreet(Street street) {
            this.street = street;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public Integer getPostcode() {
            return postcode;
        }

        public void setPostcode(Integer postcode) {
            this.postcode = postcode;
        }

        public Coordinates getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(Coordinates coordinates) {
            this.coordinates = coordinates;
        }

        public Timezone getTimezone() {
            return timezone;
        }

        public void setTimezone(Timezone timezone) {
            this.timezone = timezone;
        }

        public class Street {

            private Integer number;
            private String name;

            public Street() {
            }

            public Street(Integer number, String name) {
                this.number = number;
                this.name = name;
            }

            public Integer getNumber() {
                return number;
            }

            public void setNumber(Integer number) {
                this.number = number;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public class Coordinates {

            private String latitude;
            private String longitude;

            public Coordinates() {
            }

            public Coordinates(String latitude, String longitude) {
                this.latitude = latitude;
                this.longitude = longitude;
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
        }

        public class Timezone {

            private String offset;
            private String description;

            public Timezone() {
            }

            public Timezone(String offset, String description) {
                this.offset = offset;
                this.description = description;
            }

            public String getOffset() {
                return offset;
            }

            public void setOffset(String offset) {
                this.offset = offset;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }

    }

    public class Login {

        private String uuid;
        private String username;
        private String password;
        private String salt;
        private String md5;
        private String sha1;
        private String sha256;

        public Login() {
        }

        public Login(String uuid, String username, String password, String salt, String md5, String sha1, String sha256) {
            this.uuid = uuid;
            this.username = username;
            this.password = password;
            this.salt = salt;
            this.md5 = md5;
            this.sha1 = sha1;
            this.sha256 = sha256;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public String getMd5() {
            return md5;
        }

        public void setMd5(String md5) {
            this.md5 = md5;
        }

        public String getSha1() {
            return sha1;
        }

        public void setSha1(String sha1) {
            this.sha1 = sha1;
        }

        public String getSha256() {
            return sha256;
        }

        public void setSha256(String sha256) {
            this.sha256 = sha256;
        }
    }

    public class Dob {

        private String date;
        private Integer age;

        public Dob() {
        }

        public Dob(String date, Integer age) {
            this.date = date;
            this.age = age;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }

    public class Registered {

        private String date;
        private Integer age;

        public Registered() {
        }

        public Registered(String date, Integer age) {
            this.date = date;
            this.age = age;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }

    public class Id {

        private String name;
        private String value;

        public Id() {
        }

        public Id(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public class Picture {

        private String large;
        private String medium;
        private String thumbnail;

        public Picture() {
        }

        public Picture(String large, String medium, String thumbnail) {
            this.large = large;
            this.medium = medium;
            this.thumbnail = thumbnail;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }
    }

}
