import { Component, OnInit, Input, OnChanges, SimpleChanges } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { ChatService } from 'src/app/services/chat.service';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit, OnChanges {

  @Input() conversation: any;
  @Input() selectedUser: any;
  messages: any;


  constructor(private userService: UserService,
    private chatService: ChatService) { }

  ngOnInit() {

  }

  ngOnChanges(changes: SimpleChanges) {
    this.getMessages(this.conversation);
  }

  getMessages(conversation) {
    if (conversation) {
      this.chatService.getMessages(conversation.id).subscribe(
        (data: any) => {
          this.messages = data;
        },
        (error) => {
          alert(error);
        }
      );
    }
  }

  myMessage(message) {
    return message.sender_id === this.userService.getUser()?.id;
  }

  messageTime(message) {
    return (new Date(message.timestamp * 1000)).toLocaleString();
  }

  messageSender(message) {
    if (message) {
      return message.sender_id === this.userService.getUser().id ? this.userService.getUser().name : this.selectedUser.name;
    }
  }
}
