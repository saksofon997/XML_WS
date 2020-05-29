set services=%*
FOR /d %%i IN (".\MainApp\Servers\*") DO (
	for %%a in (%services%) do (
		echo %%i|find "%%a" >nul
		if errorlevel 1 (
		echo.
		) else (
				echo Executing maven package for service %%a ...
				cd %%i
				call mvn clean package -DskipTests 
				cd ../../..
		)
	)
)
