import numpy as np
from PIL import Image
from os import listdir

for item in listdir("/".join(__file__.split("/")[:-1])):
    if item.split(".")[1] == "png":
        img = np.array(Image.open(item))
        alpha = np.zeros_like(img)
        for i, row in enumerate(img):
            for j, pixel in enumerate(row):
                alpha[i][j] = 0 if pixel == 0 else 255
                img[i][j] = 0 if pixel != 0 else pixel
        img = Image.fromarray(img)
        alpha = Image.fromarray(alpha).convert("L")
        img.putalpha(alpha)
        img = img.convert("RGBA")
        img.save(item)
