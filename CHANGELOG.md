# Commander Change Log

## Version 1.0.1

Second update of Commander mod. This update now makes Commander mod depend upon McLib (version 1.0.2) mod, and also adds multiple commands in line parsing.

* Added multiple command parsin in one line using `||` construct
* Added `mclib` 1.0.2 as a dependency (which in turns allow to use same math builder system as in McLib which has more functions)

## Version 1.0

Initial release of Commander adds very basic features.

<a href="https://youtu.be/R_Nnu-iLj1c"><img src="https://img.youtube.com/vi/R_Nnu-iLj1c/0.jpg"></a> 

* Added `/forin <start> <end> <command...>` command
* Added `@{...}` constuction which can be used within commands to execute mathematical expressions, it supports same features as Aperture's [math modifier](https://github.com/mchorse/aperture/wiki/Math-Expressions)
* Added `i`, `j`, `k`,  `i_c`, `j_c`, `k_c`, `i_s`, `j_s`, `k_s`,  `x`,  `y`, `z` variables