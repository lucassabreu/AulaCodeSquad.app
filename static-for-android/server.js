"use strict";

var express = require("express");

var app = new express();

app.get("/", function (req, res, next) {
    res.json([
        {
            id : 1,
            nome : "Leonardo",
            curso : "Android"
        },
        {
            id : 2,
            nome : "Carla",
            curso : "php"
        }
    ]);
});

app.listen(8080, function() {
    console.log("started");
});