import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FormsModule } from '@angular/forms';
import { MatTabsModule } from '@angular/material/tabs';
import { MatTableModule } from '@angular/material/table';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatIconModule } from '@angular/material/icon';

import { SharedModule } from '../shared/shared.module';
import { ChatRoutingModule } from './chat-routing.module';
import { ChatMainComponent } from './chat-main.component';
import { ConversationsComponent } from './conversations/conversations.component';
import { MessagesComponent } from './messages/messages.component';


@NgModule({
  declarations: [
    ChatMainComponent,
    ConversationsComponent,
    MessagesComponent
  ],
  imports: [ // Import anything you would need to use in this module (forms...)
    CommonModule,
    FormsModule,

    // MatTabsModule,
    // FormsModule,
    // MatTableModule,
    // MatDialogModule,
    // MatFormFieldModule,
    // MatInputModule,
    // MatButtonModule,
    // MatInputModule,
    // MatSelectModule,
    // MatIconModule,

    ChatRoutingModule,
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class ChatModule { }
