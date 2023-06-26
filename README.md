# ClojureBlocks

Create Clojure programs in your browser.

Built with [Blockly](https://github.com/google/blockly). Blockly is a library from Google for building beginner-friendly block-based programming languages.

Evaluation is done by [sci](https://github.com/babashka/sci).

## Run
1. `npm install`
2. `npx shadow-cljs watch clojureblocks`

or try it out in your browser: [ClojureBlocks live](https://jhandke.codeberg.page/clojureblocks/)

## Example
You can find a saved workspace with an implementation of the [marsrover kata](https://codingdojo.org/kata/mars-rover/) in the example folder.
The `print-state` function will print to the developer console.

See also the [Clojure counterpart](https://codeberg.org/jhandke/marsrover.clj) of this implementation.