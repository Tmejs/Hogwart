describe("cenzor", function () {
    jasmine.clock().install();

     beforeEach( function () {
        let s = spyOn(console, 'log').and.callThrough();
        $('body').append(`

        <input class="inp"><input>
         <input class="inp2"><input>
              `);
    });

    it("text litery walidacja", function () {
        $('.inp').setText("abcd");
        $('.inp').validate('\d+');
        expect($('.inp').css("background-color")).toEqual("rgb(255, 0, 0)");
    });

    it("text cyfry walidacja", function () {
        $('.inp').setText('1234');
        $('.inp').validate('\d+');
        expect($('.inp').css("background-color")).toEqual("rgb(189, 183, 107)");

    });


    it("text cyfry anty walidacja", function () {
        $('.inp').setText('1234');
        $('.inp').validate('\d+');
        $('.inp2').setText('abcd');
        $('.inp2').validate('\d+');


        expect($('.inp').css("background-color")).toEqual("rgb(189, 183, 107)");
        expect($('.inp2').css("background-color")).toEqual("rgb(255, 0, 0)");

    });

    afterEach(function () {
        $('#tcz').remove();
    });
});