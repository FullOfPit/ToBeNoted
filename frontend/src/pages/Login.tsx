import LoginPageForm from "../components/LoginPageForm";
import MainLogo from "../components/MainLogo";
import "../index.css"
import {Box} from "@mui/material";
import React, {FormEvent, useCallback, useState} from "react";
import {emptyCredentials, UserCredentials} from "../types/UserCredentials";
import axios, {AxiosError} from "axios";
import {useNavigate} from "react-router-dom";


export default function Login() {

    const [credentials, setCredentials] = useState<UserCredentials>(emptyCredentials);
    const navigate = useNavigate();

    const login = useCallback(
        async (event: FormEvent<HTMLFormElement>) => {
            event.preventDefault();
            try {
                await axios.post("/api/app-users/login", null, {
                    headers: {
                        "Authorization": "Basic " + window.btoa(`${credentials.username}:${credentials.password}`)
                    }
                })
                navigate("/")
            } catch (error) {
                if (error instanceof AxiosError && error.response && error.response.status === 404) {
                    setCredentials({...credentials, credentialsFound: "NOT_FOUND"});
                    setTimeout(() => {
                        setCredentials({...credentials, credentialsFound: "FOUND"});
                    }, 7500);
                    console.log("not found");
                } else {
                    console.log(error)
                }
            }
        }, [credentials, navigate])

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