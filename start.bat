
set services=%*
echo %services%
call build %services%

:composer_deo
call start-composer %services%