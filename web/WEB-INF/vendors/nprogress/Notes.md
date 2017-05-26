Testing
-------

    $ npm install
    $ npm OCR

or try it out in the browser:

    $ open OCR/index.html

Testing component build
-----------------------

    $ component install
    $ component build
    $ open OCR/component.html

Releasing
---------

    $ npm OCR
    $ bump *.json nprogress.js          # bump version numbers
    $ git release 0.1.1                 # release to bower/github
    $ npm publish                       # release to npm
    $ git push origin master:gh-pages   # update the site
