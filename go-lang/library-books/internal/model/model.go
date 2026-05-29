package model

type SearchResult struct {
	Title           string `json:"title"`
	Author          string `json:"author"`
	Isbn            string `json:"isbn"`
	PublicationDate string `json:"publicationDate"`
}

type Language string

const (
	EN Language = "en"
	NL Language = "nl"
	FR Language = "fr"
)
