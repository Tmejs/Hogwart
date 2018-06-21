(function( $ ) {

    $.fn.setText = function (text) {
        this.each(function () {
            var elem=$(this);
            elem.text(text);
        })
    }

    $.fn.validate = function (pattern) {
        this.each(function () {
            var elem = $( this );
            if(!elem.text().match(pattern)) {
                elem.css("background-color", "DarkKhaki")
            }
            else {
                elem.css("background-color", "red")
            }
        })
    }
}( jQuery ));
