package com.wrestling.school.service

class UniqFileStorage
{
    constructor() {
        originalFileName = ""
        uniqFileName = ""
    }

    constructor(_originalFileName : String, _uniqFileName : String) {
        originalFileName = _originalFileName
        uniqFileName = _uniqFileName
    }

    var originalFileName : String
        get() {
            return field
        }
        set(value) {field = value}

    var uniqFileName : String
}