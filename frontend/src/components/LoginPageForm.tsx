import {Box, FormControl, IconButton, InputAdornment, InputLabel, OutlinedInput, TextField} from "@mui/material";
import React, {useState} from "react";
import {Visibility, VisibilityOff} from "@mui/icons-material";
import {emptyCredentials, UserCredentials} from "../types/UserCredentials";


export default function LoginPageForm () {

    const [credentials, setCredentials] = useState<UserCredentials>(emptyCredentials)

    const [showPassword, setShowPassword] = useState(false);

    const handleClickShowPassword = () => setShowPassword((show) => !show);
    const handleMouseDownPassword = (event: React.MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
    };

    return (
        <Box>
            <FormControl
                sx={{ m: 1, width: '25ch' }}
                variant="outlined"
            >

                <TextField
                    label="Username"
                />

            </FormControl>

            <FormControl
                sx={{ m: 1, width: '25ch' }}
                variant="outlined"
            >
                <InputLabel htmlFor="outlined-adornment-password">Password</InputLabel>

                <OutlinedInput
                    id="outlined-adornment-password"
                    type={showPassword ? 'text' : 'password'}
                    label="Password"
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
        </Box>
    )
}