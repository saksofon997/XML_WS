import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTable } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { PricelistDialogBoxComponent } from '../pricelist-dialog-box/pricelist-dialog-box.component';
import { Pricelist } from 'src/app/models/Pricelist.model';
import { PricelistService } from 'src/app/services/pricelist.service';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-pricelist-table',
  templateUrl: './pricelist-table.component.html',
  styleUrls: ['./pricelist-table.component.css']
})
export class PricelistTableComponent implements OnInit {
  displayedColumns: string[] = ['name', 'pricePerDay', 'cdw', 'pricePerKm', 'discount', 'penalty', 'action'];
  dataSource: Pricelist[];
  userId = 1;

  @ViewChild(MatTable, { static: true }) table: MatTable<any>;

  constructor(public dialog: MatDialog,
    private pricelistService: PricelistService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private userService: UserService) { }

  ngOnInit(): void {
    this.userId = this.userService.getUser().id;
    this.getPricelists(this.userId);
  }

  getPricelists(userId: number) {
    this.pricelistService.getByOwner(userId).subscribe(
      (data: any) => {
        this.dataSource = data;
      },
      (error) => {
        alert(error);
      });
  }

  openDialog(action, obj) {
    obj.action = action;
    const dialogRef = this.dialog.open(PricelistDialogBoxComponent, {
      width: '400px',
      data: obj
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result.event == 'Add') {
        this.addRowData(result.data);
      } else if (result.event == 'Update') {
        this.updateRowData(result.data);
      } else if (result.event == 'Delete') {
        this.deleteRowData(result.data);
      }
    });
  }

  addRowData(pricelist) {
    pricelist.ownerId = this.userId;
    this.pricelistService.add(pricelist).subscribe(
      (data: Pricelist) => {
        this.pricelistService.getByOwner(this.userId).subscribe(
          (data: any) => {
            this.dataSource = data;
          },
          (error) => {
            alert(error);
          });
      },
      (error) => {
        alert(error);
      }
    );
  }

  updateRowData(pricelist) {
    pricelist.ownerId = this.userId;
    this.pricelistService.edit(pricelist.id, pricelist).subscribe(
      (data: Pricelist) => {
        this.dataSource = this.dataSource.filter((value, key) => {
          if (value.id === pricelist.id) {
            value.name = pricelist.name;
            value.ownerId = this.userId;
            value.pricePerDay = pricelist.pricePerDay;
            value.cdw = pricelist.cdw;
            value.pricePerKm = pricelist.pricePerKm;
            value.description = pricelist.description;
          }
          return true;
        });
      },
      (error) => {
        alert(error);
      }
    );
  }

  deleteRowData(pricelist) {
    this.pricelistService.delete(pricelist.id).subscribe(
      (data: Pricelist) => {
        this.getPricelists(this.userId);
      },
      (error) => {
        alert(error);
      }
    );
  }
}
