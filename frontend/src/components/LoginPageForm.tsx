import {
    Button,
    FormControl,
    IconButton,
    InputAdornment,
    TextField
} from "@mui/material";
import React, {useState} from "react";
import {Visibility, VisibilityOff} from "@mui/icons-material";
import {emptyCredentials, UserCredentials} from "../types/UserCredentials";
import {mainTheme} from "../themes/MainTheme";


export default function LoginPageForm () {

    const [credentials, setCredentials] = useState<UserCredentials>(emptyCredentials)

    const handleInputLoginPageForm = (event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const name = event.target.name;
        const value = event.target.value;
        setCredentials(
            {...credentials, [name]: value})
    }

    const [showPassword, setShowPassword] = useState(false);
    const handleClickShowPassword = () => setShowPassword((show) => !show);
    const handleMouseDownPassword = (event: React.MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
    };


    return (
        <form
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
                        value={credentials.username}
                        onChange={(event) => handleInputLoginPageForm(event)}
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
                        value={credentials.password}
                        onChange={(event) => handleInputLoginPageForm(event)}
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