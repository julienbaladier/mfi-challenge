#!/bin/bash

# convert to JPEG
convert "$1" "$1.jpeg"
# re-inject the converted file to the switch plugin
inject_file --plugin=switch "$1.jpeg"
