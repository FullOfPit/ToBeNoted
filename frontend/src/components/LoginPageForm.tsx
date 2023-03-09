import {
    Box, Button,
    FormControl,
    IconButton,
    InputAdornment,
    InputLabel,
    OutlinedInput,
    TextField
} from "@mui/material";
import React, {useState} from "react";
import {Visibility, VisibilityOff} from "@mui/icons-material";
import {emptyCredentials, UserCredentials} from "../types/UserCredentials";


export default function LoginPageForm () {
    
    const [credentials, setCredentials] = useState<UserCredentials>(emptyCredentials)

    const handleInputLoginPageForm = (event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const name = event.target.name;
        const value = event.target.value;
        setCredentials(
            {...credentials, [name]: value})
    }
    //Todo
    console.log(credentials);

    const [showPassword, setShowPassword] = useState(false);
    const handleClickShowPassword = () => setShowPassword((show) => !show);
    const handleMouseDownPassword = (event: React.MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
    };

    return (
        <Box className={"flex-column-centered width-full"}>
                <FormControl
                    className={"wide"}
                >
                    <TextField
                        label="Username"
                        name={"username"}
                        value={credentials.username}
                        onChange={(event) => handleInputLoginPageForm(event)}
                    />
                </FormControl>

                <FormControl
                    className={"wide spacer-micro"}
                    variant="outlined"
                >

                    <InputLabel htmlFor="outlined-adornment-password">Password</InputLabel>

                    <OutlinedInput
                        id="outlined-adornment-password"
                        type={showPassword ? 'text' : 'password'}
                        label="Password"
                        name={"password"}
                        value={credentials.password}
                        onChange={(event) => handleInputLoginPageForm(event)}
                        endAdornment={
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
                        }
                    />

                </FormControl>

                <FormControl>

                    <Button
                        variant={"outlined"}
                    >
                        Login
                    </Button>

                </FormControl>
        </Box>
    )
}