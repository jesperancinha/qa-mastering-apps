package model

type CustomerOrder struct {
	Customer string   `json:"customer"`
	Items    []string `json:"items"`
}

type Stock struct {
	Name     string `json:"name"`
	Quantity int    `json:"quantity"`
}

type Narwhal struct {
	Name string `json:"name"`
	Age  int    `json:"age"`
}

type CurrentNarwhals struct {
	Narwhals []Narwhal `json:"narwhals"`
}
