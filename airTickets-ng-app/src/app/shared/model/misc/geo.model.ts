export interface Addres {
    bank: string;
    road: string;
    neighbourhood: string;
    city_district: string;
    city: string;
    county: string;
    state: string;
    postcode: string;
    country: string;
    country_code: string;
}

export interface Geo {
    place_id: string;
    licence: string;
    osm_type: string;
    osm_id: string;
    lat: string;
    lon: string;
    display_name: string;
    address: Addres;
    boundingbox: string[];
}