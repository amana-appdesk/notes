package com.example.notesapp.data

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class RegistrationUtilTest{

    @Test
    fun `empty username returns false`(){
        val result = RegistrationUtil.testing("","","")
        assertThat(result).isFalse()
    }

    @Test
    fun `username and correctly repeated password returns true`() {
        val result = RegistrationUtil.testing(
            "Aman",
            "123",
            "123"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun `incorrect confirm password returns false`() {
        val result = RegistrationUtil.testing(
            "Aman",
            "123",
            "1234"
        )
        assertThat(result).isFalse()
    }
}