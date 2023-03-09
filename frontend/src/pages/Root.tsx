import Login from "./Login";
import {ThemeProvider} from "@mui/material";
import {mainTheme} from "../themes/MainTheme";


export default function Root() {
    
    return (
        <ThemeProvider theme={mainTheme}>
           <Login/>
        </ThemeProvider>
    )
}