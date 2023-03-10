import LoginPageForm from "../components/LoginPageForm";
import MainLogo from "../components/MainLogo";
import "../index.css"
import {Box} from "@mui/material";
import React, {FormEvent, useCallback, useState} from "react";
import {emptyCredentials, UserCredentials} from "../types/UserCredentials";
import axios from "axios";


export default function Login() {

    const [credentials, setCredentials] = useState<UserCredentials>(emptyCredentials);

    const login = useCallback(
        async (event: FormEvent<HTMLFormElement>) => {
            event.preventDefault();
            try {
                await axios.post("/api/app-users/login", null, {
                    headers: {
                        "Authorization": "Basic " + window.btoa(`${credentials.username}:${credentials.password}`)
                    }
                })
                //ToDo
                console.log("logged in")
            } catch (e) {
                //ToDo
                console.log("not logged in")
                console.log(e)
            }
        }, [credentials])

    const setUserCredentials = useCallback(
        (event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const name = event.target.name;
        const value = event.target.value;
        setCredentials(
            {...credentials, [name]: value})
        }, [credentials]
    )

    const userCredentialProp = {
        userCredentials: credentials,
        setUserCredentials: setUserCredentials,
        login: login,
    }

    return (
        <Box className={
            "flex-column " +
            "flex-centered " +
            "margin-small"}>
            <MainLogo/>
            <LoginPageForm userCredentialProp={userCredentialProp}/>
        </Box>
    )
}