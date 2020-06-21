import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ChatMainComponent } from './chat-main.component';


const routes: Routes = [
  {
    path: '',
    component: ChatMainComponent,
    canActivate: [],
    children: [
    ]
  },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ChatRoutingModule { }
