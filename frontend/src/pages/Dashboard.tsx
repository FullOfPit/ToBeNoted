import * as React from "react";
import {Card, CardContent, Stack, Typography} from "@mui/material";
import {useNavigate} from "react-router-dom";

export default function Dashboard() {

    const navigate = useNavigate();

    const pageList: { pageName: string, linkTo: string}[] = [
        {pageName: "Briefing Document", linkTo: "/brief"},
        {pageName: "Member Management", linkTo: "/staff"},
        {pageName: "Archive", linkTo: "/archive"}
    ];

    return (
        <Stack
            spacing={5}
            justifyContent={"flex-start"}
            alignItems={"center"}
            sx={{paddingTop: 50}}
        >
            {pageList.map((page) =>
                <Card
                    aria-label={"dashboard"}
                    key={page.pageName}
                    sx={{
                        maxWidth: 400,
                        minWidth: 350
                    }}>
                    <CardContent onClick={() => navigate(page.linkTo)}>

                        <Typography
                            textAlign="center"
                            variant="h5"
                            component="div">
                            {page.pageName}
                        </Typography>

                    </CardContent>
                </Card>)}
        </Stack>
    )
}