import { Actor } from "./actor";
import { Category } from "./category";
import { Director } from "./director";


export interface Movie{
    id:number;
    title:string;
    year:number;
    countCopies:number;
    director:Director;
    actors:Actor[]
    categories:Category[]

}