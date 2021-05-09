import fontforge
import sys
from os import listdir, remove


def main(font):
    F = fontforge.open(font[0])

    for item in listdir():
        if item.split(".")[1] not in ["py", "ttf", "idea"]:
            remove(item)

    digits = ["zero", "one", "two", "three",
              "four", "five", "six", "seven",
              "eight", "nine"]

    def export(nameo, filenamep):
        F[nameo].export(filenamep, 1600)
        return

    for name in F:
        if len(name) == 1 and 96 < ord(name) < 132:
            filename = name + "_lower.png"
            export(name, filename)
        elif name in digits:
            filename = digits[digits.index(name)] + ".png"
            export(name, filename)
        elif name == name[0] + ".sc":
            filename = name[0] + "_upper.png"
            export(name, filename)


if __name__ == "__main__":
    main(sys.argv[1:])
