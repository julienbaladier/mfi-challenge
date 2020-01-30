#!/usr/bin/bash

/etc/rc.d/init.d/metwork start
tail -f /home/mfdata/log/step_convert_png_to_jpeg_main.stdout
