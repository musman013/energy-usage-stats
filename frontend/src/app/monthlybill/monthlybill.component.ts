import { Component, OnInit } from '@angular/core';

import { EnergyService } from '../energy.service';

@Component({
  selector: 'app-monthlybill',
  templateUrl: './monthlybill.component.html',
  styleUrls: ['./monthlybill.component.scss']
})
export class MonthlybillComponent implements OnInit {
cost: any;
totalCost:number;
  constructor(private energyService: EnergyService) { }

  ngOnInit(): void {
    this.energyService.getTotalCost().subscribe((data: any) => {
      this.totalCost = data.cost;
    })
  }

}
