import LoginPageForm from "../components/LoginPageForm";
import MainLogo from "../components/MainLogo";
import "../index.css"
import {Box} from "@mui/material";


export default function Login() {

    return (
        <Box className={
            "flex-column " +
            "flex-centered " +
            "margin-small"}>

            <MainLogo/>
            <LoginPageForm/>

        </Box>
    )
}