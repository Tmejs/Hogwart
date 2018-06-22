#include "catch.hpp"

#include <rpn.hpp>


using Catch::Matchers::Contains;


TEST_CASE("Indeks + x")
{

    rpn Rpn =  rpn();
    REQUIRE(Rpn.count("14876 10 +") == 14886);
}

TEST_CASE("Indeks - x")
{

    rpn Rpn =  rpn();
    REQUIRE(Rpn.count("14876 10 -") == 14866);
}


TEST_CASE("Działanie")
{

    rpn Rpn = rpn();
    //10*5 + 10 -10 /10
    REQUIRE(Rpn.count("10 5 * 10 + 10 - 10 / ") == 5);
}

TEST_CASE("Error handling")
{
    rpn Rpn = rpn();
    REQUIRE_THROWS_WITH(Rpn.count("10 2 %"), Contains("%"));
}

