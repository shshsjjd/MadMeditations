package com.coolgirl.madmeditations.Models

import java.text.FieldPosition

class ResponseFeelings(
    var success:Boolean?,
    var data: List<Feelings>?
)

class Feelings (
    var id: Int?,
    var title: String?,
    var position: Int?,
    var image: String?
)