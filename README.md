# Ghost

Wraps the entire CasperJS API (as of version 1.0.0-RC1). Note: You must have [CasperJS
installed](http://casperjs.org/installation.html) already.

## Installation

```clojure
    [ghost "0.1.0-alpha1"]
```

## Usage

```clojure
    (ns mycasperproject.core
      (:require [ghost.core :as casper]))

    ;; Totally optional, otherwise a vanilla Casper instance is create()ed for you
    (casper/set-casper-options!
     {:verbose true, :viewportSize {:width 1024 :height 768}})

    (casper/user-agent
     "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/536.11 (KHTML, like Gecko) Lambdarat/23.0.1062.57")

    ;; Begin the fun
    (casper/start
     "http://www.google.com"
     (fn [] (casper/echo "Loaded Google!")))

    (casper/then
     (fn [] (casper/echo (casper/get-element-attribute :body :onload))))

    (casper/run)
```

## Known Issues

Advanced compilation with the Google Closure Compiler will produce the expected mayhem.

## License

Copyright (C) 2011 Murphy McMahon

Distributed under the Eclipse Public License, the same as Clojure.
