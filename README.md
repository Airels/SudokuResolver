# SudokuResolver

![version release](https://img.shields.io/github/v/release/Airels/SudokuResolver?style=for-the-badge)
![repo size](https://img.shields.io/github/repo-size/Airels/SudokuResolver?style=for-the-badge)
![license](https://img.shields.io/github/license/Airels/SudokuResolver?style=for-the-badge)

## Methods used by the sr.resolver :
- Unique Possibility (Inclusive Method)
- Exclusive (Inclusive Method)
- Exclusive with one or two pairs

    ***All resolution methods used explained [here](https://www.sudoku-puzzles-online.com/sudoku/how-to-solve-sudoku.php)***

**This sr.resolver CAN'T resolve hardest sudokus, stronger resolutions methods need to be added**

(Fell free to add new ones if you want)

## Dependencies
- ![node version](https://img.shields.io/npm/v/node?label=NodeJS)
    - ![npm version](https://img.shields.io/npm/v/npm)
    - ![express version](https://img.shields.io/npm/v/express?label=express)
    - ![mustache version](https://img.shields.io/npm/v/mustache-express?label=mustache-express)
    - ![electron version](https://img.shields.io/npm/v/electron?label=electron)
    - ![electron-packager version](https://img.shields.io/npm/v/electron-packager?label=electron-packager)

## npm commands
**For using user interface (located in electron directory):**
- To simply start app: ```npm start```
- To compile in an executable: ```npm run build```
    - If you compile, don't forget to create jar artifact and drop it in the same folder as executable
