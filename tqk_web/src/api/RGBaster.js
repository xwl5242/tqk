;(function(window, undefined){
  "use strict";
  // Helper functions.
  let getContext = function(width, height){
    let canvas = document.createElement("canvas");
    canvas.setAttribute('width', width);
    canvas.setAttribute('height', height);
    return canvas.getContext('2d');
  };

  let getImageData = function(img, loaded){

    let imgObj = new Image();
    let imgSrc = img.src || img;

    // Can't set cross origin to be anonymous for data url's
    // https://github.com/mrdoob/three.js/issues/1305
    if ( imgSrc.substring(0,5) !== 'data:' )
      imgObj.crossOrigin = "Anonymous";

    imgObj.onload = function(){
      let context = getContext(imgObj.width, imgObj.height);
      context.drawImage(imgObj, 0, 0);

      let imageData = context.getImageData(0, 0, imgObj.width, imgObj.height);
      loaded && loaded(imageData.data);
    };

    imgObj.src = imgSrc;

  };

  let makeRGB = function(name){
    return ['rgb(', name, ')'].join('');
  };

  let mapPalette = function(palette){
    let arr = [];
    for (let prop in palette) { arr.push( frmtPobj(prop, palette[prop]) ) };
    arr.sort(function(a, b) { return (b.count - a.count) });
    return arr;
  };

  let fitPalette = function(arr, fitSize) {
    if (arr.length > fitSize ) {
      return arr.slice(0,fitSize);
    } else {
      for (let i = arr.length-1 ; i < fitSize-1; i++) { arr.push( frmtPobj('0,0,0', 0) ) };
      return arr;
    };
  };

  let frmtPobj = function(a,b){
    return {name: makeRGB(a), count: b};
  }


  // RGBaster Object
  // ---------------
  //
  let PALETTESIZE = 10;

  let RGBaster = {};

  RGBaster.colors = function(img, opts){

    opts = opts || {};
    let exclude = opts.exclude || [ ], // for example, to exclude white and black:  [ '0,0,0', '255,255,255' ]
      paletteSize = opts.paletteSize || PALETTESIZE;

    getImageData(img, function(data){

      let colorCounts   = {},
        rgbString     = '',
        rgb           = [],
        colors        = {
          dominant: { name: '', count: 0 },
          palette:  []
        };

      let i = 0;
      for (; i < data.length; i += 4) {
        rgb[0] = data[i];
        rgb[1] = data[i+1];
        rgb[2] = data[i+2];
        rgbString = rgb.join(",");

        // skip undefined data and transparent pixels
        if (rgb.indexOf(undefined) !== -1  || data[i + 3] === 0) {
          continue;
        }

        // Ignore those colors in the exclude list.
        if ( exclude.indexOf( makeRGB(rgbString) ) === -1 ) {
          if ( rgbString in colorCounts ) {
            colorCounts[rgbString] = colorCounts[rgbString] + 1;
          }
          else{
            colorCounts[rgbString] = 1;
          }
        }

      }

      if ( opts.success ) {
        let palette = fitPalette( mapPalette(colorCounts), paletteSize+1 );
        opts.success({
          dominant: palette[0].name,
          secondary: palette[1].name,
          palette:  palette.map(function(c){ return c.name; }).slice(1)
        });
      }
    });
  };

  window.RGBaster = window.RGBaster || RGBaster;


})(window);
export default RGBaster
