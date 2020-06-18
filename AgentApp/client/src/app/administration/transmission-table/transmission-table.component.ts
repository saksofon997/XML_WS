import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTable } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { DialogBoxComponent } from '../dialog-box-edit/dialog-box-edit.component';
import { Transmission } from 'src/app/models/Transmission.model';
import { TransmissionService } from 'src/app/services/transmission.service';
import { MatPaginator } from '@angular/material/paginator';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-transmission-table',
  templateUrl: './transmission-table.component.html',
  styleUrls: ['./transmission-table.component.css']
})
export class TransmissionTableComponent implements OnInit {
  displayedColumns: string[] = ['name', 'action'];
  dataSource: Transmission[];
  pageNo: number;
  totalPages: number;

  @ViewChild(MatTable, { static: true }) table: MatTable<any>;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(public dialog: MatDialog,
    private transmissionService: TransmissionService,
    private router: Router,
    private activatedRoute: ActivatedRoute) {
    this.pageNo = 0;
    this.getTransmissions(this.pageNo);
  }

  ngOnInit(): void {
  }

  getTransmissions(pageNo: number) {
    // this.transmissionService.getPageable(pageNo).subscribe(
    //   (data: any) => {
    //     this.dataSource = data.content;
    //     this.pageNo = data.pageNo;
    //     this.totalPages = data.totalPages;
    //   },
    //   (error) => {
    //     alert(error);
    //   }
    // );
    this.transmissionService.getAll().subscribe(
      (data: any) => {
        this.dataSource = data;
      },
      (error) => {
        alert(error);
      }
    )
  }

  openDialog(action, obj) {
    obj.action = action;
    const dialogRef = this.dialog.open(DialogBoxComponent, {
      width: '300px',
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

  addRowData(transmission) {
    this.transmissionService.add(transmission).subscribe(
      (data: Transmission) => {
        this.getTransmissions(this.pageNo);
      },
      (error) => {
        alert(error);
      }
    );
  }
  updateRowData(transmission) {
    this.transmissionService.edit(transmission.id, transmission).subscribe(
      (data: Transmission) => {
        this.dataSource = this.dataSource.filter((value, key) => {
          if (value.id === transmission.id) {
            value.name = transmission.name;
          }
          return true;
        });
      },
      (error) => {
        alert(error);
      }
    );
  }
  deleteRowData(transmission) {
    this.transmissionService.delete(transmission.id).subscribe(
      (data: Transmission) => {
        this.getTransmissions(this.pageNo);
      },
      (error) => {
        alert(error);
      }
    );
  }
}
