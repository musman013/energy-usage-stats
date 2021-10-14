import { EnergyService } from "../energy.service";

export class dataClass {
    
    constructor (private energySer:EnergyService){}
    dataSet = [
        { data: [this.energySer.coalCost, 59, 15,30,50,20,34,54,23,10,27], label: 'Coal',  },
        { data: [this.energySer.gasCost, 12, 20,40,60,10,30,15,34,23,32], label: 'Gas' },
        { data: [this.energySer.elecCost, 12, 20,32,20,40,20,10,44,55,63], label: 'Electricity' }
        
      ];
}