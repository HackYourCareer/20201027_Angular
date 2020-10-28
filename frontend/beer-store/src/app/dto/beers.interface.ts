export interface BeerDto {
    name: string;
    img: string;
    style: string;
    alc: string;
    ibu: string;
}

export interface BeerDetailDto extends BeerDto {
    hops: string[];
    description: string;
}
