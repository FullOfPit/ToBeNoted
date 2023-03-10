import LoginPageForm from "../components/LoginPageForm";
import MainLogo from "../components/MainLogo";
import "../index.css"
import {Box} from "@mui/material";
import React, {FormEvent, useCallback, useState} from "react";
import {emptyCredentials, UserCredentials} from "../types/UserCredentials";
import axios from "axios";


export default function Login() {

    const [credentials, setCredentials] = useState<UserCredentials>(emptyCredentials);

    const userCredentialProp = {
        userCredentials: credentials,
        setUserCredentials: (event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
            const name = event.target.name;
            const value = event.target.value;
            setCredentials(
                {...credentials, [name]: value})
        }
    }

    const login = useCallback(
        async (event: FormEvent<HTMLFormElement>) => {
            event.preventDefault();
            try {
                await axios.post("/api/app-users/login", null, {
                    headers: {
                        "Authorization": "Basic " + window.btoa(`${credentials.username}:${credentials.password}`)
                    }
                })
                console.log("logged in")
            } catch (e) {
                console.log(e)
            }
        },
        [credentials])

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