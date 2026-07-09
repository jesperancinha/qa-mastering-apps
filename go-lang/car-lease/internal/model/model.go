package model

type CarDto struct {
	ID           int64  `json:"id"`
	Make         string `json:"make"`
	Model        string `json:"model"`
	Version      string `json:"version"`
	NumberDoors  int64  `json:"numberDoors"`
	Co2Emission  int64  `json:"co2Emission"`
	GrossPrice   int64  `json:"grossPrice"`
	NetPrice     int64  `json:"netPrice"`
	Millage      int64  `json:"millage"`
}
