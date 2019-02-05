import { RentACar } from "./rentacar.model";
import { BranchOffice } from "./branchOffice.model";

export interface RentacarsWithBranches {
    rentacar: RentACar;
    branches: BranchOffice[];
}