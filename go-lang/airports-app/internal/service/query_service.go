package service

import (
	"github.com/jesperancinha/qa-mastering-apps/go-lang/airports-app/internal/model"
	"strings"
)

type QueryAirportService struct {
	FullAirportInfo []model.Airport
}

func (s *QueryAirportService) GetAirportsByCountryName(countryName string) []model.Airport {
	var result []model.Airport
	for _, airport := range s.FullAirportInfo {
		if airport.Country != nil && strings.EqualFold(airport.Country.Name, countryName) {
			result = append(result, airport)
		}
	}
	return result
}

func (s *QueryAirportService) GetAirportsByCountryCode(countryCode string) []model.Airport {
	var result []model.Airport
	for _, airport := range s.FullAirportInfo {
		if strings.EqualFold(airport.IsoCountry, countryCode) {
			result = append(result, airport)
		}
	}
	return result
}
