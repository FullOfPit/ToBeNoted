type PageDescription = {
    pageName: string,
    description: string,
    internalLink?: string,
}
export type {PageDescription};

const DashBoard: PageDescription = {
    pageName: "Dashboard",
    description: "The dashboard contains all necessary links and shows recent changes",
    internalLink: "/"
}

const BriefingDocument: PageDescription = {
    pageName: "Briefing Document",
    description: "The briefing document lets you pick "
}