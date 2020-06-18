import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
	loading = false;
	submitted = false;
	returnUrl: string = '';

	constructor(
		private formBuilder: FormBuilder,
		private route: ActivatedRoute,
		private router: Router,
		private userService: UserService
		// private authenticationService: AuthenticationService,
	) { }

	ngOnInit() {
		this.loginForm = this.formBuilder.group({
			email: ['', [Validators.required, Validators.email]],
			password: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(24)]]
		});
	}

	onSubmit() {
		this.submitted = true;

		if (this.loginForm.invalid) {
			return;
		}

		this.loading = true;
		this.userService.login(this.loginForm.controls.email.value, this.loginForm.controls.password.value)
			.pipe()
			.subscribe(
				data => {
					//this.router.navigate([this.returnUrl]);
				},
				error => {
					this.loading = false;
					alert(error);
				});
	}
}
