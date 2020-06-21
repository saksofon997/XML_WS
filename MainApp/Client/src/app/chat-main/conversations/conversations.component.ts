import { Component, OnInit, Input, Output, EventEmitter, OnChanges, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-conversations',
  templateUrl: './conversations.component.html',
  styleUrls: ['./conversations.component.css']
})
export class ConversationsComponent implements OnInit, OnChanges {

  @Input() conversations: any;
  @Input() selectedUser: any;
  @Output() selectConversation: EventEmitter<any> = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  ngOnChanges(changes: SimpleChanges) {

  }

  getConversationClass(user) {
    return user.email === this.selectedUser?.email ? true : false;
  }

  selectConversationFunc(conversation) {
    this.selectConversation.emit(conversation);
  }

}
