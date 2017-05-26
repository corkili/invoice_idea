beforeEach ->
  placeholder = $('<div id="graph" style="width: 600px; height: 400px"></div>')
  $('#OCR').append(placeholder)

afterEach ->
  $('#OCR').empty()
