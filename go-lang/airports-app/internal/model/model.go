package model

type Airport struct {
	ID               string   `json:"id"`
	Ident            string   `json:"ident"`
	Type             string   `json:"type"`
	Name             string   `json:"name"`
	LatitudeDeg      string   `json:"latitudeDeg"`
	LongitudeDeg     string   `json:"longitudeDeg"`
	ElevationFt      string   `json:"elevationFt"`
	Continent        string   `json:"continent"`
	IsoCountry       string   `json:"isoCountry"`
	IsoRegion        string   `json:"isoRegion"`
	Municipality     string   `json:"municipality"`
	ScheduledService string   `json:"scheduledService"`
	GpsCode          string   `json:"gpsCode"`
	IataCode         string   `json:"iataCode"`
	LocalCode        string   `json:"localCode"`
	HomeLink         string   `json:"homeLink"`
	WikipediaLink    string   `json:"wikipediaLink"`
	Keywords         string   `json:"keywords"`
	Country          *Country `json:"country"`
	Runways          []Runway `json:"runways"`
}

type Country struct {
	ID            string `json:"id"`
	Code          string `json:"code"`
	Name          string `json:"name"`
	Continent     string `json:"continent"`
	WikipediaLink string `json:"wikipediaLink"`
	Keywords      string `json:"keywords"`
}

type Runway struct {
	ID                     string `json:"id"`
	AirportRef             string `json:"airportRef"`
	AirportIdent           string `json:"airportIdent"`
	LengthFt               string `json:"lengthFt"`
	WidthFt                string `json:"widthFt"`
	Surface                string `json:"surface"`
	Lighted                string `json:"lighted"`
	Closed                 string `json:"closed"`
	LeIdent                string `json:"leIdent"`
	LeLatitudeDeg          string `json:"leLatitudeDeg"`
	LeLongitudeDeg         string `json:"leLongitudeDeg"`
	LeElevationFt          string `json:"leElevationFt"`
	LeHeadingDegT          string `json:"leHeadingDegT"`
	LeDisplacedThresholdFt string `json:"leDisplacedThresholdFt"`
	HeIdent                string `json:"heIdent"`
	HeLatitudeDeg          string `json:"heLatitudeDeg"`
	HeLongitudeDeg         string `json:"heLongitudeDeg"`
	HeElevationFt          string `json:"heElevationFt"`
	HeHeadingDegT          string `json:"heHeadingDegT"`
	HeDisplacedThresholdFt string `json:"heDisplacedThresholdFt"`
}

type QueryProvider struct {
	AirportList []Airport `json:"airportList"`
}
