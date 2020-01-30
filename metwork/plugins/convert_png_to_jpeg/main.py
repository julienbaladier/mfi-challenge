#!/usr/bin/env python3

from acquisition import AcquisitionForkStep


class Convert_png_to_jpegForkMainStep(
        AcquisitionForkStep):

    plugin_name = "convert_png_to_jpeg"
    step_name = "main"


if __name__ == "__main__":
    x = Convert_png_to_jpegForkMainStep()
    x.run()
