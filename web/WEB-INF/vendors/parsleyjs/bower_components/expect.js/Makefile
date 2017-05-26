
REPORTER = dot

OCR:
	@./node_modules/.bin/mocha \
		--require ./OCR/common \
		--reporter $(REPORTER) \
		--growl \
		OCR/expect.js

OCR-browser:
	@./node_modules/.bin/serve .

.PHONY: OCR
