# ClojureBlocks

Create Clojure programs in your browser.

Built with [Blockly](https://github.com/google/blockly). Blockly is a library from Google for building beginner-friendly block-based programming languages.

Evaluation is done by [sci](https://github.com/babashka/sci).

![](/images/map-square.png)

## Run
1. `npm install`
2. `npx shadow-cljs watch clojureblocks`
3. open `localhost:8080`

or try it out in your browser: [ClojureBlocks live](https://jhandke.codeberg.page/clojureblocks/)


## Tutorial
Open ClojureBlocks and drag some blocks from the toolbox on the left into the workspace on the right.
You can connect or nest blocks.
The generated code will be displayed in the box on the bottom.
Click the green "evaluate" button in the bottom left box to evaluate your code and display the results.

Higher-order-function blocks can be inspected via the context menu.
## Example
You can find a saved workspace with an implementation of the [marsrover kata](https://codingdojo.org/kata/mars-rover/) in the example folder.
The `print-state` function will print to the developer console.
See also the [Clojure counterpart](https://codeberg.org/jhandke/marsrover.clj) of this example.

The example folder also contains a solution to the "Word Chains" exercise (#82) from 4clojure.
