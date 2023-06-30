import {
    Button,
    FormControl,
    IconButton,
    InputAdornment,
    TextField, Typography, useTheme
} from "@mui/material";
import React, {FormEvent, useState} from "react";
import {Visibility, VisibilityOff} from "@mui/icons-material";
import {UserCredentials} from "../types/UserCredentials";


export default function LoginPageForm (
    {
        userCredentialProp,
    }:{
        userCredentialProp: {
            userCredentials: UserCredentials,
            setUserCredentials: (event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => void,
            login: (event: FormEvent<HTMLFormElement>) => void
        }
    }) {

    const [showPassword, setShowPassword] = useState(false);
    const handleClickShowPassword = () => setShowPassword((show) => !show);
    const handleMouseDownPassword = (event: React.MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
    };

    const mainTheme = useTheme();

    return (
        <form
            onSubmit={(event) => userCredentialProp.login(event)}
            className={"flex-column-centered width-full"}
        >
                <FormControl
                    sx={{
                        width: "80%",
                        margin: mainTheme.spacing(1),
                    }}
                >
                    <TextField
                        variant={"standard"}
                        label="Username"
                        name={"username"}
                        value={userCredentialProp.userCredentials.username}
                        onChange={(event) => userCredentialProp.setUserCredentials(event)}
                    />

                </FormControl>

                <FormControl
                    sx={{
                        width: "80%",
                        margin: mainTheme.spacing(1),
                    }}
                >
                    <TextField
                        variant={"standard"}
                        type={showPassword ? 'text' : 'password'}
                        label="Password"
                        name={"password"}
                        value={userCredentialProp.userCredentials.password}
                        onChange={(event) => userCredentialProp.setUserCredentials(event)}
                        InputProps={{
                            endAdornment:
                                <InputAdornment position="end">
                                    <IconButton
                                    aria-label="toggle password visibility"
                                    onClick={handleClickShowPassword}
                                    onMouseDown={handleMouseDownPassword}
                                    edge="end"
                                    >
                                    {showPassword ? <VisibilityOff /> : <Visibility />}
                                    </IconButton>

                                </InputAdornment>
                        }}
                    />

                    {
                        userCredentialProp.userCredentials.credentialsFound === "NOT_FOUND" &&
                        <Typography aria-label={"warning"}>
                            Username or password incorrect!
                        </Typography>
                    }

                </FormControl>

                <FormControl
                    sx={{
                        width: "50%",
                        margin: mainTheme.spacing(1),
                    }}
                >
                    <Button type={"submit"}>
                        Login
                    </Button>
                </FormControl>
        </form>
    )
}